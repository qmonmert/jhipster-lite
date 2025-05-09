import { defineComponent, ref } from 'vue';

import { GLOBAL_WINDOW, inject } from '@/injections';
import { MODULES_REPOSITORY } from '@/module/application/ModuleProvider';
import { Project } from '@/module/domain/Project';
import { ALERT_BUS } from '@/shared/alert/application/AlertProvider';
import { IconVue } from '@/shared/icon/infrastructure/primary';

export default defineComponent({
  name: 'ProjectActionsVue',
  components: {
    IconVue,
  },
  props: {
    folderPath: {
      type: String,
      required: true,
    },
    isPrettierButtonEnabled: {
      type: Boolean,
      required: true,
    },
  },
  emits: ['operationStarted', 'operationEnded'],
  setup(props, { emit }) {
    const globalWindow = inject(GLOBAL_WINDOW);
    const alertBus = inject(ALERT_BUS);
    const modules = inject(MODULES_REPOSITORY);

    const operationInProgress = ref(false);

    const disabledActions = (): boolean => {
      return operationInProgress.value || empty(props.folderPath);
    };

    const disabledPrettier = (): boolean => {
      return disabledActions() || !props.isPrettierButtonEnabled;
    };

    const formatProject = (): void => {
      startOperation();

      modules
        .format(props.folderPath)
        .then(() => {
          endOperation();

          alertBus.success('Project formatted');
        })
        .catch(() => {
          endOperation();

          alertBus.error("Project can't be formatted");
        });
    };

    const downloadProject = (): void => {
      startOperation();

      modules
        .download(props.folderPath)
        .then(project => {
          download(project);
        })
        .catch(() => {
          alertBus.error("Project can't be downloaded");
        })
        .finally(() => {
          endOperation();
        });
    };

    const download = (project: Project): void => {
      const url = globalWindow.URL.createObjectURL(new Blob([project.content]));
      const link = globalWindow.document.createElement('a');
      link.href = url;
      link.download = project.filename;
      globalWindow.document.body.appendChild(link);
      link.click();

      globalWindow.URL.revokeObjectURL(link.href);
      globalWindow.document.body.removeChild(link);
    };

    const startOperation = (): void => {
      operationInProgress.value = true;
      emit('operationStarted');
    };

    const endOperation = (): void => {
      operationInProgress.value = false;
      emit('operationEnded');
    };

    return {
      disabledActions,
      disabledPrettier,
      formatProject,
      downloadProject,
    };
  },
});

const empty = (value: string): boolean => value.trim().length === 0;
